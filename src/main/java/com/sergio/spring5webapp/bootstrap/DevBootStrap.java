package com.sergio.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.sergio.spring5webapp.model.Author;
import com.sergio.spring5webapp.model.Book;
import com.sergio.spring5webapp.model.Publisher;
import com.sergio.spring5webapp.repositories.AuthorRepository;
import com.sergio.spring5webapp.repositories.BookRepository;
import com.sergio.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;

	public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		initData();
	}

	private void initData() {

		// Eric
		Author eric = new Author("Eric", "Evans");
		Publisher harperCollins = new Publisher("Harper Collins", "Rua do Bingo");
		Book ddd = new Book("Domain Driver Design", "1234", harperCollins);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		authorRepository.save(eric);
		publisherRepository.save(harperCollins);
		bookRepository.save(ddd);

		// Rod
		Author rod = new Author("Rod", "Johnson");
		Publisher povoaEditora = new Publisher("Povoa Editora", "Avenida dos Banhos");
		Book noEJB = new Book("J2EE Development without EJB", "23444", povoaEditora);
		rod.getBooks().add(noEJB);

		authorRepository.save(rod);
		publisherRepository.save(povoaEditora);
		bookRepository.save(noEJB);

	}

}
