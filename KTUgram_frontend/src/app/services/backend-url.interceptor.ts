import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class BackEndUrlInterceptor implements HttpInterceptor {

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (request.url.indexOf('KTUgramLogo.png') === -1) {
            const cloneRequest = request.clone({
                url: environment.backendUrl + request.url
            });

            return next.handle(cloneRequest);
        }

        return next.handle(request);
    }
}
