import { Roles } from './../../models/roles';
import { RolesService } from './../../services/roles.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-roles',
  templateUrl: './list-roles.component.html',
  styleUrls: ['./list-roles.component.css']
})
export class ListRolesComponent implements OnInit {

  roles: Roles[] = []

  constructor(private rolesService: RolesService) { }

  ngOnInit(): void {
    this.getAllRoles()
  }

  getAllRoles() {
    this.rolesService.getAll().subscribe(res => console.log(res));
  }

}
