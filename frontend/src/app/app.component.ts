import {Component, OnInit} from '@angular/core';
import {Movie} from '../model/Movie';
import {MovieService} from './movie.service';
import {HttpErrorResponse} from '@angular/common/http';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  public movies: Movie[];
  editMovie: Movie;
  deleteMovie: Movie;

  ngOnInit(): void {
    this.getMovies();
  }

  constructor(private movieService: MovieService) {}

  public getMovies(): void {
    this.movieService.getMovies().subscribe(
      (response: Movie[]) => {
        this.movies = response;
        console.log(this.movies);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddMovie(addForm: NgForm): void{
    document.getElementById('add-movie-form').click();
    this.movieService.addMovie(addForm.value).subscribe(
      (response: Movie) => {
        console.log(response);
        this.getMovies();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onDeleteMovie(id: number): void {
    this.movieService.deleteMovie(id).subscribe(
      (response: void) => {
        console.log(response);
        this.getMovies();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateMovie(movie: Movie): void{
    this.movieService.updateMovie(movie).subscribe(
      (response: Movie) => {
        console.log(response);
        this.getMovies();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(movie: Movie, mode: string): void{
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if (mode === 'add'){
      button.setAttribute('data-target', '#addMovieModal');
    }

    if (mode === 'update'){
      this.editMovie = movie;
      button.setAttribute('data-target', '#updateMovieModal');
    }

    if (mode === 'delete'){
      this.deleteMovie = movie;
      button.setAttribute('data-target', '#deleteMovieModal');
    }
    container.appendChild(button);
    button.click();
  }

  public searchMovies(key: string): void {
    console.log(key);
    const results: Movie[] = [];
    // @ts-ignore
    for (const movie of this.movies) {
      if (movie.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || movie.category.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || movie.directoryName.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || movie.publishDate.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(movie);
      }
    }
    this.movies = results;
    if (results.length === 0 || !key) {
      this.getMovies();
    }
  }




}
