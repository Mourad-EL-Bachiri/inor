package com.inor.demo.sevices;

import java.util.List;

import com.inor.demo.shared.dto.MatiereDto;

public interface MatiereService {

	MatiereDto createMatiere(MatiereDto matiereDto);

	MatiereDto getMatiere(String id);

	MatiereDto updateMatiere(String id, MatiereDto matiereDto);

	void deleteMatiere(String id);

	List<MatiereDto> getMatieres(int page, int limit);
	

}
