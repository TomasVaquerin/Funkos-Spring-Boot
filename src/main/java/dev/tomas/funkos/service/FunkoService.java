package dev.tomas.funkos.service;

import dev.tomas.common.Service;
import dev.tomas.funkos.dto.FunkoDto;
import dev.tomas.funkos.models.Funko;

import java.util.UUID;

public interface FunkoService extends Service <Funko, UUID, FunkoDto> {
}
