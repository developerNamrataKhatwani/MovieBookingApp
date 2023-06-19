import { TestBed } from '@angular/core/testing';

import { MovieService } from './movie.service';
import { HttpClientTestingModule, HttpTestingController, } from '@angular/common/http/testing';

fdescribe('MovieService', () => {
  let service: MovieService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MovieService]
    });
    service = TestBed.inject(MovieService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  fit('should be created', () => {
    expect(service).toBeTruthy();
  });

  fit('getAllMovies should send a Get request ', () => {
    const getAllMovies = [{
      movieName: "fdds",

      theatreName: "Kingdom",

      totalNoseats: 100,

      seatsAvailable: 100,

      audio: "English",

      genre: "",

      imgUrl: ""

    },
    { movieName: "Test2",

      theatreName: "Cinepolis",

      totalNoseats: 100,

      seatsAvailable: 100,

      audio: "English",

      genre: "Nahi hai",

      imgUrl: ""
    }];
    // const response = 'Success';

    service.getAllMovie().subscribe((res) => {
      expect(res).toEqual(getAllMovies);
    });

    const req = httpMock.expectOne('http://localhost:8081/api/v1/movieBooking/user/searchAll');
    expect(req.request.method).toBe('GET');
    req.flush(getAllMovies);
  });
});
