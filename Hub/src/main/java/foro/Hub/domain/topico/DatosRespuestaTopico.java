package foro.Hub.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
        String autor,
        String curso,
        LocalDateTime fecha
) {

}
