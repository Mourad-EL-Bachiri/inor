import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get(`${environment.apiUrl}/roles`)
  }
}
