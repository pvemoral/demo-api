package com.api.demo.services;

import com.api.demo.models.Client;
import com.api.demo.models.ClientDTO;
import com.api.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Mapper methods
    private ClientDTO convertToDTO(Client client) {
        return new ClientDTO(client.getName(), client.getAddress(), client.getPhone(), client.getDni());
    }

    private Client convertToEntity(ClientDTO clientDTO) {
        return new Client(clientDTO.getName(), clientDTO.getAddress(), clientDTO.getPhone(), clientDTO.getDni());
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ClientDTO> getClientById(Integer id) {
        return clientRepository.findById(id).map(this::convertToDTO);
    }

    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = convertToEntity(clientDTO);
        return convertToDTO(clientRepository.save(client));
    }

    public Optional<ClientDTO> updateClient(Integer id, ClientDTO clientDTO) {
        return clientRepository.findById(id)
                .map(existingClient -> {
                    existingClient.setName(clientDTO.getName());
                    existingClient.setAddress(clientDTO.getAddress());
                    existingClient.setPhone(clientDTO.getPhone());
                    existingClient.setDni(clientDTO.getDni());
                    return convertToDTO(clientRepository.save(existingClient));
                });
    }

    public boolean deleteClient(Integer id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
