-- Criação da tabela de tipos de livros
CREATE TABLE TiposDeLivro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL
);

-- Preenchendo os tipos de livros
INSERT INTO TiposDeLivro (tipo) VALUES ('romance'), ('ficção'), ('técnico');

-- Criação da tabela de usuários
CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    sexo VARCHAR(50) NOT NULL,
    tipo VARCHAR(50) DEFAULT 'comum', 
    senha VARCHAR(255) NOT NULL,
    livroPreferido1 INT,
    livroPreferido2 INT,
    FOREIGN KEY (livroPreferido1) REFERENCES TiposDeLivro(id),
    FOREIGN KEY (livroPreferido2) REFERENCES TiposDeLivro(id)
);

-- Criação da tabela de livros
CREATE TABLE Livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    tipoID INT,
    mediaNotas DECIMAL(4,2) DEFAULT 0.00,
    FOREIGN KEY (tipoID) REFERENCES TiposDeLivro(id)
);

-- Criação da tabela de avaliações
CREATE TABLE Avaliacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    livroID INT,
    nota DECIMAL(4,2),
    FOREIGN KEY (userID) REFERENCES Usuarios(id),
    FOREIGN KEY (livroID) REFERENCES Livros(id)
);
