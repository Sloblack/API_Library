package com.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Stock;
import com.libraryproject.repository.StockRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StockService {
    @Autowired
	private StockRepository repo;

	public List<Stock> getAll() {
		return repo.findAll();
	}

	public void save(Stock stock) {
		repo.save(stock);
	}

		//For use with MongoDB:
	// public Stock getByIdStock(Integer id) {
	// 	return repo.findById(id).get();
	// }

	// public void delete(Integer id) {
	// 	repo.deleteById(id);
	// }
	
		//For use with MySQL:
	public Stock getByIdStock(Integer idStock) {
		return repo.findById(idStock).get();
	}

	public void delete(Integer idStock) {
		repo.deleteById(idStock);
	}
}