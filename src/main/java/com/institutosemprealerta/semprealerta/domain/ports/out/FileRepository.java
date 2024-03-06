package com.institutosemprealerta.semprealerta.domain.ports.out;

import com.institutosemprealerta.semprealerta.domain.model.File;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.FileResponse;

import java.util.List;

public interface FileRepository {
    void save(File file);
    void delete(Long id);
    List<FileResponse> listAll();

}
