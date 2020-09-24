import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  {
    path: '', children: [
      {path: '', loadChildren: () => import('./authorization/authorization.module').then(m => m.AuthorizationModule)},
    ]
  },
  {
    path: 'authorization',
    loadChildren: () => import('./authorization/authorization.module').then(m => m.AuthorizationModule)
  },
  {path: 'main', loadChildren: () => import('./main/main.module').then(m => m.MainModule)}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
