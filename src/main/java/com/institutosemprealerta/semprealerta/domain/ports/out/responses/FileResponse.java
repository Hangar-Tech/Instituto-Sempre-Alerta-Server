package com.institutosemprealerta.semprealerta.domain.ports.out.responses;

import java.time.LocalDateTime;

public record FileResponse(
        long id,
        String fileName,
        String fileDownloadUri,
        String fileType,
        LocalDateTime uploadDate
) {
}
