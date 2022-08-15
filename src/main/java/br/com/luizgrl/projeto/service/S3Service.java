package br.com.luizgrl.projeto.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {
    @Autowired
    private AmazonS3 s3client;
    @Value("${s3.bucket}")
    private String bucketName;


    public URI updloadFile(MultipartFile multipartFile) { // serve para pegar arquivo epla url e retorna um URI para mandar o endereço url do objeto criado
        try{
            String fileName = multipartFile.getOriginalFilename(); // vai extrair o nome do arquivo enviado
            InputStream is = multipartFile.getInputStream(); // encapsula o objeto pela origem
            String contentType = multipartFile.getContentType(); // Recebe o tipo de arquivo
            return updloadFile(is, fileName, contentType);

        }catch ( IOException e){
            throw new RuntimeException("Erro de IO");

        }

    }
    public URI updloadFile (InputStream is, String name, String contentType){

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            s3client.putObject(bucketName,name,is,objectMetadata);
            return s3client.getUrl(bucketName,name).toURI();

        }catch (URISyntaxException e){
            throw  new RuntimeException("Erro ao converter URL para URI");

        }


        }

}
