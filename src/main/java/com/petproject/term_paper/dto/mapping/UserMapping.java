package com.petproject.term_paper.dto.mapping;

import com.petproject.term_paper.dto.UserDTO;
import com.petproject.term_paper.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapping {
    UserDTO toDTO(User user);
}
