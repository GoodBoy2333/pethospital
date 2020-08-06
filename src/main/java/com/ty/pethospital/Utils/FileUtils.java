package com.ty.pethospital.Utils;

import cn.hutool.core.io.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * @author fy
 * @Date 2019/11/19 22:32
 */
public class FileUtils {

    public static Path getPath() throws IOException {
        LocalDate now = LocalDate.now();
        Path path = Paths.get(now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth());
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }
        return path;
    }

    public static Path uploadFile(MultipartFile file, Long id) throws IOException {
        Path path = getPath();
        //Path resolve = path.resolve(file.getOriginalFilename());
        String fileName = id + "." + FileUtil.extName(file.getOriginalFilename());
        Path resolve = path.resolve(fileName);
        if (Files.exists(resolve)) {
            Files.deleteIfExists(resolve);
        }
        file.transferTo(resolve);
        return resolve;
    }
}
