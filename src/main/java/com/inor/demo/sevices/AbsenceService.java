package com.inor.demo.sevices;

import java.util.List;


import com.inor.demo.shared.dto.AbsenceDto;

public interface AbsenceService{

	AbsenceDto createAbsence(AbsenceDto absenceDto);

	AbsenceDto getAbsence(String id);

	List<AbsenceDto> getAbsences(int page, int limit);

	AbsenceDto updateAbsence(String id, AbsenceDto absenceDto);

	void deleteAbsence(String id);

}
