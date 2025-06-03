package com.library_a3.library_a3.shared.dtos;

import com.library_a3.library_a3.shared.enums.Role;

public record LoginResponseDTO(String token, Role role) {
}
