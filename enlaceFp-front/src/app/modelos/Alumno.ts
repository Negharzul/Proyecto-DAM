
/*
interface Titulo{
  id:number;
  nombre:String;
}


interface Empleo{
  id:number;
  nombre:String;
}


export class Alumno{
  constructor(
    public id?: number,
    public nombre?: string,
    public apellidos?: string,
    public email?: string,
    public titulos?: Titulo[],
    public empleos?: Empleo[]
  ) {}



  mostrarEmpleosAlumno():string{
    return this.empleos?.map(e => e.nombre).join(', ') ?? '';
  }
  mostrarTitulosAlumno():string{
    return this.titulos?.map(t => t.nombre).join(', ') ?? '';
  }


}
  */
export interface Alumno {
  id: number
  nombre: string
  apellidos: string
  email: string
  empleos: Empleos[]
  titulos: string[]

}


export interface Empleos {
  "1": string
  "2": string
}


