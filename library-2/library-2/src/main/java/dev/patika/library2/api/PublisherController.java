package dev.patika.library2.api;


import dev.patika.library2.business.abstracts.IPublisherService;

import dev.patika.library2.dto.CategoryDto;
import dev.patika.library2.dto.PublisherDto;
import dev.patika.library2.dto.UpdatePublisherRequest;
import dev.patika.library2.entities.Book;
import dev.patika.library2.entities.Category;
import dev.patika.library2.entities.Publisher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class PublisherController {

    @Autowired
    private IPublisherService publisherService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/publishers")
    public List<PublisherDto> findAll() {
        List<Publisher> publishers = publisherService.findAll();

        List<PublisherDto> publisherDtos = publishers.stream()
                .map(publisher -> {
                    PublisherDto publisherDto = modelMapper.map(publisher, PublisherDto.class);

                    // Book adlarını al
                    List<String> bookNames = publisher.getBookList().stream()
                            .map(Book::getName)
                            .collect(Collectors.toList());

                    // Book adlarını set et
                    publisherDto.setBookNames(bookNames);
                    return publisherDto;
                })
                .collect(Collectors.toList());

        return publisherDtos;
    }

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody Publisher publisher) {
        try {
            Publisher savedPublisher = this.publisherService.save(publisher);
            String successMessage = this.publisherService.getPublisherSuccessMessage(savedPublisher);
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } catch (Exception e) {
            String errorMessage = this.publisherService.getPublisherErrorMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }



    @PutMapping("/publishers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> update(@RequestBody UpdatePublisherRequest updatePublisherRequest) {
        try {

            Publisher existingPublisher = this.publisherService.getById(updatePublisherRequest.getId());

            existingPublisher.setName(updatePublisherRequest.getName());
            existingPublisher.setAddress(updatePublisherRequest.getAddress());



            this.publisherService.update(existingPublisher);

            // Başarı mesajını döndür
            String successMessage = "Yayıncı başarıyla güncellendi. Yayıncı ID: " + existingPublisher.getId();
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (Exception e) {
            // Hata durumunda uygun hata mesajını ve 500 Internal Server Error durumunu geri döndür
            String errorMessage = "Yayıncı güncellenirken bir hata oluştu.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


    @DeleteMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            this.publisherService.delete(id);
            return ResponseEntity.ok(publisherService.getPublisherDeleteSuccessMessage(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(publisherService.getPublisherDeleteErrorMessage());
        }
    }

    @GetMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher getById(@PathVariable("id") int id){
        return this.publisherService.getById(id);
    }

}
