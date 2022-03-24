package com.inor.demo.sevices;

import java.util.List;

import com.inor.demo.shared.dto.EtudiantDto;

public interface EtudiantService {

	EtudiantDto createEtu(EtudiantDto etudiantDto);

	EtudiantDto getEtu(String id);

	List<EtudiantDto> getAllEtu(int page, int limit);

	EtudiantDto updateEtu(String id, EtudiantDto etudiantDto);

	void deleteEtu(String id);

	
}
