package foro.Hub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
         @NotNull
         Long id,
         String mensaje,
         Status status
) {
}
