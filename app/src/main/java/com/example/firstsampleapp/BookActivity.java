package com.example.firstsampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    public static final String BOOK_ID_KEY = "bookId";
    private Button btnAddToCurrentlyReading,btnAddToWantToRead,btnAddToAlreadyRead, btnAddToFavourites;
    private TextView txtAddToBookName, txtAddToAuthor, txtAddToPages, txtAddToLongDesc;
    private ImageView bookImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();
        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if( bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if(null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavouriteBooks(incomingBook);
                }
            }
        }
        

    }

    private void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReading = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existsInCurrentlyReading = false;

        for (Book b : currentlyReading) {
            if (b.getId()==book.getId()) {
                existsInCurrentlyReading = true;
            }
        }

        if (existsInCurrentlyReading){
            btnAddToCurrentlyReading.setEnabled(false);
        }else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void handleFavouriteBooks(final Book book) {
        ArrayList<Book> favouriteBooks = Utils.getInstance(this).getFavouriteBooks();

        boolean existsInFavouriteBooks = false;

        for (Book b : favouriteBooks) {
            if (b.getId()==book.getId()) {
                existsInFavouriteBooks = true;
            }
        }

        if (existsInFavouriteBooks){
            btnAddToFavourites.setEnabled(false);
        }else {
            btnAddToFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToFavourite(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, FavouriteActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existsInWantToReadBooks = false;

        for (Book b : wantToReadBooks) {
            if (b.getId()==book.getId()) {
                existsInWantToReadBooks = true;
            }
        }

        if (existsInWantToReadBooks){
            btnAddToWantToRead.setEnabled(false);
        }else {
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }



    }

    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existsInAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks) {
            if (b.getId()==book.getId()) {
                existsInAlreadyReadBooks = true;
            }
        }

        if (existsInAlreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false);
        }else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity .this, AlreadyReadBooksActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                    
                }
            });
        }
    }

    private void setData(Book book) {
        txtAddToBookName.setText(book.getName());
        txtAddToAuthor.setText(book.getAuthor());
        txtAddToPages.setText(String.valueOf(book.getPages()));
        txtAddToLongDesc.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);

    }

    private void initViews() {
        txtAddToAuthor = findViewById(R.id.txtAddToAuthor);
        txtAddToBookName = findViewById(R.id.txtAddToBookName);
        txtAddToPages = findViewById(R.id.txtAddToPages);
        txtAddToLongDesc = findViewById(R.id.txtAddToLongDesc);

        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavourites = findViewById(R.id.btnAddToFavourites);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);

        bookImage = findViewById(R.id.bookImage);
    }
}