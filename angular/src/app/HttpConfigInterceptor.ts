import { HttpInterceptor,HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
@Injectable()
export class HttpConfigInterceptor implements HttpInterceptor{

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let url: string = req.url;
        let authReq = req;

        if (url.includes('http://localhost')) {

            console.log(url);

            let token = window.sessionStorage.getItem('Token');

            if (token != null)
                authReq = req.clone({
                    headers: req.headers.append('Authorization', 'Bearer ' + token)
                });
        }
        return next.handle(authReq);
    }

}