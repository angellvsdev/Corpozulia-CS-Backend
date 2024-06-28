package com.corpozulia.counting.service;

import com.corpozulia.counting.models.Item;
import com.corpozulia.counting.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Método para crear un nuevo item
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // Método para obtener todos los items
    public Page<Item> getAllItems(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return itemRepository.findAll(pageable);
    }

    // Método para obtener un item por su id
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    // Método para actualizar un item
    public Item updateItem(Long id, Item newItem) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setDescription(newItem.getDescription());
                    item.setQuantity(newItem.getQuantity());
                    return itemRepository.save(item);
                })
                .orElse(null);
    }

    // Método para eliminar un item
    public boolean deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
    /**
     * Buscar items por su nombre.
     *
     * @param name Nombre a buscar en los items
     * @param page Número de página (comienza desde 0)
     * @param size Tamaño de la página
     * @return Page con los items encontrados que coincidan con el nombre
     */
    public Page<Item> searchItemsByName(String name, int page, int size) {
        return itemRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size));
    }
}

