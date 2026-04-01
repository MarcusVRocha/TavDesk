ALTER TABLE chamados_tb
    ADD COLUMN usuario_id BIGINT;

ALTER TABLE chamados_tb
    ADD CONSTRAINT fk_chamados_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuarios_tb(id);