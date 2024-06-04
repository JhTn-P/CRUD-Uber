package com.example.util;

import java.util.List;

import com.example.model.MediaMensalViagens;

public class ResultadoMediaMensalViagens {
    private List<MediaMensalViagens> mediasMensais;

    public ResultadoMediaMensalViagens(List<MediaMensalViagens> mediasMensais) {
        this.mediasMensais = mediasMensais;
    }

    public List<MediaMensalViagens> getMediasMensais() {
        return mediasMensais;
    }

    public boolean isEmpty() {
        return mediasMensais.isEmpty();
    }

    @Override
    public String toString() {
        return "Médias Mensais de Viagens: " + mediasMensais;
    }
}
