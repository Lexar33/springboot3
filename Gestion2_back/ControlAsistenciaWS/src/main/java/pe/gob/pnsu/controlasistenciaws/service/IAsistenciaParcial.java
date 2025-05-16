package pe.gob.pnsu.controlasistenciaws.service;

import pe.gob.pnsu.controlasistenciaws.util.RestResponse;

public interface IAsistenciaParcial {

    RestResponse registrarcontrolasistenciaparcial(String desde, String hasta);

}
