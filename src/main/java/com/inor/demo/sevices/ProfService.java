package com.inor.demo.sevices;

import java.util.List;

import com.inor.demo.shared.dto.ProfDto;

public interface ProfService {

	ProfDto createProf(ProfDto profDto);

	ProfDto getProf(String id);

	List<ProfDto> getAllprofs(int page, int limit);

	ProfDto updateProf(String id, ProfDto profDto);

	void deleteProf(String id);
	

}
