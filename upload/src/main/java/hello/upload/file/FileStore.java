package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//파일저장과 관련된 업무 처리
@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    //파일경로 + 파일이름 불러오기
    public String getFullPath(String filename) {
        return fileDir + filename;
    }


    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        
        String storeFileName = createStoreFileName(originalFileName); //서버에 저장하는 파일명

        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFileName, storeFileName);
    }

    private String createStoreFileName(String originalFileName) {
        String ext = extractExt(originalFileName);   //확장자 가져오기

        String uuid = UUID.randomUUID().toString();  //최종적으로 서버에 저장할 파일명
        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }


}
