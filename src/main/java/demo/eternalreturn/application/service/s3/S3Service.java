package demo.eternalreturn.application.service.s3;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadFile(MultipartFile file, Long id);
}
