import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DataService } from '../data.service';
import { RequestDto } from '../models/requestdto';
import { ResponseDto } from '../models/responsedto';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  formDto = {} as RequestDto;
  desafioResponse = {} as ResponseDto;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }

  save(form: NgForm) {
    this.desafioResponse = {} as ResponseDto;
    this.dataService.processaDesafio(this.formDto).subscribe(data => {
      this.desafioResponse = data;
      this.cleanForm(form);
    })
  }

    // limpa o formulario
    cleanForm(form: NgForm) {
      form.resetForm();
      this.formDto = {} as RequestDto;
    }

}
