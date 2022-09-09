
package com.vucic.booklist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vucic.booklist.models.Book;
import com.vucic.booklist.repository.BooksRepositoryFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView booksRecyclerView = findViewById(R.id.booksRecyclerView);
        EditText searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);

        List<Book> bookList = BooksRepositoryFactory.getBooksRepository().getBooks();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        booksRecyclerView.setLayoutManager(layoutManager);
        booksRecyclerView.setAdapter(new BooksAdapter(bookList, this));
        booksRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, layoutManager.getOrientation()));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String authorString = String.valueOf(searchEditText.getText());
                List<Book> bookByAuthorList =
                        BooksRepositoryFactory.getBooksRepository().getBooksByAuthor(authorString);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                booksRecyclerView.setLayoutManager(layoutManager);
                booksRecyclerView.setAdapter(new BooksAdapter(bookByAuthorList, MainActivity.this));
                booksRecyclerView.addItemDecoration(new DividerItemDecoration(
                        MainActivity.this, layoutManager.getOrientation()));
            }
        });
    }
}