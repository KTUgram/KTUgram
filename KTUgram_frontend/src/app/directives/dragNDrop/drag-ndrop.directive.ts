import {Directive, EventEmitter, HostBinding, HostListener, Output} from '@angular/core';

@Directive({
  selector: '[appDragNDrop]'
})
export class DragNDropDirective {

  constructor() { }

  @Output() dropEvent: EventEmitter<any> = new EventEmitter<any>();

  @HostBinding('class.fileOver') fileOver: boolean = false;

  @HostListener('dragover', ['$event']) onDragOver(event: any){
    event.preventDefault();
    event.stopPropagation();
    this.fileOver = true;
  }

  @HostListener('dragleave', ['$event']) onDragLeave(event: any){
    this.fileOver = false;
    event.preventDefault();
    event.stopPropagation();
  }

  @HostListener('drop', ['$event']) onDrop(event: any){
    this.fileOver = false;
    event.preventDefault();
    event.stopPropagation();
    console.log(event.dataTransfer.files[0])
    this.dropEvent.emit(event.dataTransfer.files[0])
  }

}
