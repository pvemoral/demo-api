package com.api.demo.services;

import com.api.demo.models.Client;
import com.api.demo.models.ClientDTO;
import com.api.demo.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client1;
    private Client client2;
    private ClientDTO clientDTO1;

    @BeforeEach
    void setUp() {
        client1 = new Client(1, "John Doe", "123 Main St", "555-1234", "12345678A");
        client2 = new Client(2, "Jane Smith", "456 Oak Ave", "555-5678", "87654321B");
        clientDTO1 = new ClientDTO("John Doe", "123 Main St", "555-1234", "12345678A");
    }

    @Test
    void getAllClients_shouldReturnListOfClientDTOs() {
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        List<ClientDTO> result = clientService.getAllClients();

        assertEquals(2, result.size());
        assertEquals(client1.getName(), result.get(0).getName());
        assertEquals(client2.getName(), result.get(1).getName());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void getClientById_whenClientExists_shouldReturnClientDTO() {
        when(clientRepository.findById(1)).thenReturn(Optional.of(client1));

        Optional<ClientDTO> result = clientService.getClientById(1);

        assertTrue(result.isPresent());
        assertEquals(client1.getName(), result.get().getName());
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    void getClientById_whenClientDoesNotExist_shouldReturnEmptyOptional() {
        when(clientRepository.findById(1)).thenReturn(Optional.empty());

        Optional<ClientDTO> result = clientService.getClientById(1);

        assertFalse(result.isPresent());
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    void createClient_shouldReturnCreatedClientDTO() {
        when(clientRepository.save(any(Client.class))).thenReturn(client1);

        ClientDTO result = clientService.createClient(clientDTO1);

        assertNotNull(result);
        assertEquals(clientDTO1.getName(), result.getName());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void updateClient_whenClientExists_shouldReturnUpdatedClientDTO() {
        Client updatedClient = new Client(1, "Johnathan Doe", "123 Main St New", "555-1235", "12345678C");
        ClientDTO updatedClientDTO = new ClientDTO("Johnathan Doe", "123 Main St New", "555-1235", "12345678C");

        when(clientRepository.findById(1)).thenReturn(Optional.of(client1));
        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        Optional<ClientDTO> result = clientService.updateClient(1, updatedClientDTO);

        assertTrue(result.isPresent());
        assertEquals(updatedClientDTO.getName(), result.get().getName());
        assertEquals(updatedClientDTO.getAddress(), result.get().getAddress());
        assertEquals(updatedClientDTO.getPhone(), result.get().getPhone());
        assertEquals(updatedClientDTO.getDni(), result.get().getDni());
        verify(clientRepository, times(1)).findById(1);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void updateClient_whenClientDoesNotExist_shouldReturnEmptyOptional() {
        ClientDTO updatedClientDTO = new ClientDTO("Johnathan Doe", "123 Main St New", "555-1235", "12345678C");
        when(clientRepository.findById(1)).thenReturn(Optional.empty());

        Optional<ClientDTO> result = clientService.updateClient(1, updatedClientDTO);

        assertFalse(result.isPresent());
        verify(clientRepository, times(1)).findById(1);
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    void deleteClient_whenClientExists_shouldReturnTrue() {
        when(clientRepository.existsById(1)).thenReturn(true);
        doNothing().when(clientRepository).deleteById(1);

        boolean result = clientService.deleteClient(1);

        assertTrue(result);
        verify(clientRepository, times(1)).existsById(1);
        verify(clientRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteClient_whenClientDoesNotExist_shouldReturnFalse() {
        when(clientRepository.existsById(1)).thenReturn(false);

        boolean result = clientService.deleteClient(1);

        assertFalse(result);
        verify(clientRepository, times(1)).existsById(1);
        verify(clientRepository, never()).deleteById(1);
    }
}
