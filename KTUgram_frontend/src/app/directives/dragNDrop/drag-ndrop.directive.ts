import {Directive, HostBinding, HostListener} from '@angular/core';

@Directive({
  selector: '[appDragNDrop]'
})
export class DragNDropDirective {

  constructor() { }

  @HostBinding('class.fileOver') fileOver: boolean = false;

  @HostListener('dragover', ['$event']) onDragOver(event: any){
    console.log("Drag over");
    event.preventDefault();
    event.stopPropagation();
    this.fileOver = true;
  }

  @HostListener('dragleave', ['$event']) onDragLeave(event: any){
    this.fileOver = false;
    event.preventDefault();
    event.stopPropagation();
  }

}
