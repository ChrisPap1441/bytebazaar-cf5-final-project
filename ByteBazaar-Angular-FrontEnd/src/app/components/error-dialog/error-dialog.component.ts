import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef  } from '@angular/material/dialog';
import { SafeHtmlPipe } from 'src/app/safe-html.pipe';

@Component({
  selector: 'app-error-dialog',
  standalone: true,
  imports: [SafeHtmlPipe],
  templateUrl: './error-dialog.component.html',
  styleUrl: './error-dialog.component.scss'
})
export class ErrorDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { message: string }, public dialogRef: MatDialogRef<ErrorDialogComponent>) { }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
