package com.thaheshan.bookstoreapplication.Resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;


//import all need models for the book resource class
import src.main.java.Models.Book;
import src.main.java.Models.Author;
import src.main.java.Models.Customer;
import src.main.java.Models.Cart;
import src.main.java.Models.Order;

// In-memory storage
//create a database class for store the trasacation of book resources
class Database {

    //create static hashmap for,
    static Map<Integer, src.main.java.Models.Book> books = new HashMap<>();
    static Map<Integer, src.main.java.Models.Author> authors = new HashMap<>();
    static Map<Integer, src.main.java.Models.Customer> customers = new HashMap<>();
    static Map<Integer, src.main.java.Models.Cart> carts = new HashMap<>();
    static Map<Integer, List<src.main.java.Models.Order>> orders = new HashMap<>();

    static int bookIdCounter = 1;
    static int authorIdCounter = 1;
    static int customerIdCounter = 1;
    static int orderIdCounter = 1;
}

// Book Resource
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)

public class BookResource {
    @POST
    public Response createBook(Book book) {
        book.SetBook_ID(Database.bookIdCounter++);
        if (!Database.authors.containsKey(Book.getAuthor_ID())) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Author Not Found"))
                    .build();
        }
        Database.books.put(book.getBook_ID(), book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @GET
    public Response getAllBooks() {
        return Response.ok(Database.books.values()).build();
    }

    @GET
    @Path("/{id}")
    public Response getBook(@PathParam("id") int id) {
        Book book = Database.books.get(id);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Book Not Found"))
                    .build();
        }
        return Response.ok(book).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") int id, Book updatedBook) {
        Book book = Database.books.get(id);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Book Not Found")).build();
        }
        updatedBook.SetBook_ID(id);
        Database.books.put(id, updatedBook);
        return Response.ok(updatedBook).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        Book book = Database.books.remove(id);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Book Not Found")).build();
        }
        return Response.noContent().build();
    }
}

// Author Resource
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AuthorResource {
    @POST
    public Response createAuthor(Author author) {
        author.SetAuthor_ID(Database.authorIdCounter++);
        Database.authors.put(author.GetAuthor_ID(), author);
        return Response.status(Response.Status.CREATED).entity(author).build();
    }

    @GET
    public Response getAllAuthors() {
        return Response.ok(Database.authors.values()).build();
    }

    @GET
    @Path("/{id}")
    public Response getAuthor(@PathParam("id") int id) {
        Author author = Database.authors.get(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Author Not Found")).build();
        }
        return Response.ok(author).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        Author author = Database.authors.get(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Author Not Found")).build();
        }
        updatedAuthor.SetAuthor_ID(id);
        Database.authors.put(id, updatedAuthor);
        return Response.ok(updatedAuthor).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        Author author = Database.authors.remove(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Author Not Found")).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/books")
    public Response getBooksByAuthor(@PathParam("id") int id) {
        List<Book> authoredBooks = new ArrayList<>();
        for (Book book : Database.books.values()) {
            if (book.getAuthorId() == id) {
                authoredBooks.add(book);
            }
        }
        return Response.ok(authoredBooks).build();
    }
}

// Customer Resource
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CustomerResource {
    @POST
    public Response createCustomer(Customer customer) {
        customer.setId(Database.customerIdCounter++);
        Database.customers.put(customer.getId(), customer);
        Database.carts.put(customer.getId(), new Cart(customer.getId()));
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @GET
    public Response getAllCustomers() {
        return Response.ok(Database.customers.values()).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") int id) {
        Customer customer = Database.customers.get(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Customer Not Found")).build();
        }
        return Response.ok(customer).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer updatedCustomer) {
        Customer customer = Database.customers.get(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Customer Not Found")).build();
        }
        updatedCustomer.setId(id);
        Database.customers.put(id, updatedCustomer);
        return Response.ok(updatedCustomer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        Customer customer = Database.customers.remove(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Customer Not Found")).build();
        }
        Database.carts.remove(id);
        return Response.noContent().build();
    }
}
