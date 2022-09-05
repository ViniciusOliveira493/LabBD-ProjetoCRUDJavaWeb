CREATE DATABASE bdProdutos
GO
USE bdProdutos
CREATE TABLE tbMarca(
	id			INT				NOT NULL
	, nome		VARCHAR(50)		NOT NULL
	PRIMARY KEY (id)
);

CREATE TABLE tbProduto(
	id			INT				NOT NULL
	, nome		VARCHAR(50)		NOT NULL
	, preco		DECIMAL(7,2)	NOT NULL
	, idMarca	INT				NOT NULL
	PRIMARY KEY (id)
	FOREIGN KEY (idMarca) REFERENCES tbMarca(id)
);
--============================================
