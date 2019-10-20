package org.verizon.documentservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.verizon.documentservices.dao.DocumentRepository;
import org.verizon.documentservices.model.Document;
import org.verizon.documentservices.services.DocumentService;

/**
 * Starting point of the document service
 * 
 * @author Shankar K
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "org.verizon.documentservices" })
@EnableMongoRepositories(basePackages = { "org.verizon.documentservices.dao" })
public class DocumentServicesController implements CommandLineRunner {

	@Autowired
	private DocumentService documentService;

	@Autowired
	private DocumentRepository repository;

	@Override
	public void run(String... args) throws Exception {
		this.repository.deleteAll();		
		
		Document doc1 = new Document();
		doc1.setCategory("RPF");
		doc1.setCreatedAt("");
		doc1.setCreator("ShaNkar");
		doc1.setUser("ShaNkar");
		doc1.setDocName("MyDocu1Group");
		doc1.setDocType("txt");
		doc1.setGroup("My1");
		doc1.setDocId("1s");
		doc1.setUserEmail("test1@gmail.com");
		
		Document doc2 = new Document();
		doc2.setCategory("ContractGroup");
		doc2.setCreatedAt("");
		doc2.setCreator("Pranith");
		doc2.setUser("Pranith");
		doc2.setDocName("MyDocu2");
		doc2.setDocType("txt");
		doc2.setGroup("Test2");
		doc2.setDocId("2d");
		doc2.setUserEmail("test2@gmail.com");
		
		Document doc3 = new Document();
		doc3.setCategory("Quote");
		doc3.setCreatedAt("");
		doc3.setCreator("Abhi");
		doc3.setUser("Abhi");
		doc3.setDocName("MyDocu3");
		doc3.setDocType("xls");
		doc3.setGroup("MyGroup3");
		doc3.setDocId("3c");
		doc3.setUserEmail("test3@gmail.com");
		
		// save a couple of Documents
		this.repository.save(doc1);
		this.repository.save(doc2);
		this.repository.save(doc3);

		System.out.println("Document found with findByDocuName('Notes'):");
		System.out.println("--------------------------------");
		System.out.println(documentService.findByDocuName("Notes"));

		System.out.println("Documents found with findByDocuType('pdf'):");
		System.out.println("--------------------------------");
		for (Document Document : documentService.findByDocuType("pdf")) {
			System.out.println(Document);
		}

		System.out.println("Documents found with findByDocuNamePattern('pdf'):");
		System.out.println("--------------------------------");
		for (Document Document : documentService.findByDocuNamePattern("ot")) {
			System.out.println(Document);
		}
	}

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(DocumentServicesController.class, args);
	}
}
