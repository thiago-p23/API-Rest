const API_URL = 'http://localhost:8080/api';

const CONFIG_ENTIDADES = {
    'clientes': {
        titulo: 'Clientes',
        campos: [
            { nome: 'nome', label: 'Nome Completo', tipo: 'text' },
            { nome: 'telefone', label: 'Telefone', tipo: 'text' },
            { nome: 'cpf', label: 'CPF (somente números)', tipo: 'text' }
        ]
    },
    'veiculos': {
        titulo: 'Veículos',
        campos: [
            { nome: 'placa', label: 'Placa', tipo: 'text' },
            { nome: 'modelo', label: 'Modelo', tipo: 'text' },
            { nome: 'ano', label: 'Ano', tipo: 'number' },
            { nome: 'clienteId', label: 'ID do Cliente', tipo: 'number' }
        ]
    },
    'mecanicos': {
        titulo: 'Mecânicos',
        campos: [
            { nome: 'nome', label: 'Nome do Mecânico', tipo: 'text' },
            { nome: 'especialidade', label: 'Especialidade', tipo: 'text' }
        ]
    },
    'servicos': {
        titulo: 'Serviços',
        campos: [
            { nome: 'descricao', label: 'Descrição do Serviço', tipo: 'text' },
            { nome: 'valor', label: 'Valor (R$)', tipo: 'number', step: '0.01' }
        ]
    },
    'ordens-servico': {
        titulo: 'Ordens de Serviço',
        campos: [
            { nome: 'veiculoId', label: 'ID do Veículo', tipo: 'number' },
            { nome: 'mecanicoId', label: 'ID do Mecânico', tipo: 'number' },
            { nome: 'servicoId', label: 'ID do Serviço', tipo: 'number' },
            { nome: 'status', label: 'Status (Ex: Aberta, Concluída)', tipo: 'text' },
            { nome: 'dataAbertura', label: 'Data', tipo: 'date' }
        ]
    }
};

let entidadeAtual = 'clientes';

const form = document.getElementById('registro-form');
const camposDiv = document.getElementById('campos-dinamicos');
const inputId = document.getElementById('registro-id');
const btnCancelar = document.getElementById('btn-cancelar');
const feedbackDiv = document.getElementById('feedback');
const loadingDiv = document.getElementById('loading');
const vaziaDiv = document.getElementById('lista-vazia');
const tabela = document.getElementById('tabela-dados');

function mostrarFeedback(mensagem, tipo) {
    feedbackDiv.textContent = mensagem;
    feedbackDiv.className = `feedback ${tipo}`;
    setTimeout(() => feedbackDiv.classList.add('hidden'), 4000);
}

function renderizarFormulario() {
    const config = CONFIG_ENTIDADES[entidadeAtual];
    document.getElementById('form-titulo').textContent = `Gerenciar ${config.titulo}`;
    document.getElementById('lista-titulo').textContent = `Lista de ${config.titulo}`;
    camposDiv.innerHTML = '';

    config.campos.forEach(campo => {
        const div = document.createElement('div');
        div.className = 'campo';
        div.innerHTML = `
            <label for="campo-${campo.nome}">${campo.label}</label>
            <input type="${campo.tipo}" id="campo-${campo.nome}" name="${campo.nome}" ${campo.step ? `step="${campo.step}"` : ''} required>
        `;
        camposDiv.appendChild(div);
    });
    resetarFormulario();
}

function resetarFormulario() {
    form.reset();
    inputId.value = '';
    btnCancelar.classList.add('hidden');
}

async function carregarDados() {
    loadingDiv.classList.remove('hidden');
    vaziaDiv.classList.add('hidden');
    tabela.classList.add('hidden');

    try {
        const res = await fetch(`${API_URL}/${entidadeAtual}`);
        const dados = await res.json();

        loadingDiv.classList.add('hidden');

        if (!res.ok) {
            mostrarFeedback(dados.erro || 'Erro ao buscar dados.', 'erro');
            return;
        }

        if (dados.length === 0) {
            vaziaDiv.classList.remove('hidden');
            return;
        }

        renderizarTabela(dados);
    } catch (error) {
        loadingDiv.classList.add('hidden');
        mostrarFeedback('Falha de conexão com a API. Verifique se o servidor está rodando.', 'erro');
    }
}

function renderizarTabela(dados) {
    const config = CONFIG_ENTIDADES[entidadeAtual];
    const thead = document.getElementById('tabela-head');
    const tbody = document.getElementById('tabela-body');

    thead.innerHTML = `<tr><th>ID</th>${config.campos.map(c => `<th>${c.label}</th>`).join('')}<th>Ações</th></tr>`;
    tbody.innerHTML = '';

    dados.forEach(item => {
        const tr = document.createElement('tr');
        const colunas = config.campos.map(c => `<td>${item[c.nome]}</td>`).join('');
        tr.innerHTML = `
            <td>${item.id}</td>
            ${colunas}
            <td>
                <button class="btn-editar" onclick='prepararEdicao(${JSON.stringify(item)})'>Editar</button>
                <button class="btn-excluir" onclick="excluirRegistro(${item.id})">Excluir</button>
            </td>
        `;
        tbody.appendChild(tr);
    });

    tabela.classList.remove('hidden');
}

window.prepararEdicao = (item) => {
    inputId.value = item.id;
    CONFIG_ENTIDADES[entidadeAtual].campos.forEach(c => {
        document.getElementById(`campo-${c.nome}`).value = item[c.nome];
    });
    btnCancelar.classList.remove('hidden');
    window.scrollTo({ top: 0, behavior: 'smooth' });
};

window.excluirRegistro = async (id) => {
    if (!confirm('Deseja realmente excluir este registro?')) return;
    try {
        const res = await fetch(`${API_URL}/${entidadeAtual}/${id}`, { method: 'DELETE' });
        if (!res.ok) {
            const data = await res.json().catch(() => ({}));
            mostrarFeedback(data.erro || 'Erro ao excluir.', 'erro');
            return;
        }
        mostrarFeedback('Registro excluído com sucesso!', 'sucesso');
        carregarDados();
    } catch {
        mostrarFeedback('Erro de conexão ao tentar excluir.', 'erro');
    }
};

form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = inputId.value;
    const payload = {};

    CONFIG_ENTIDADES[entidadeAtual].campos.forEach(c => {
        const val = document.getElementById(`campo-${c.nome}`).value;
        payload[c.nome] = c.tipo === 'number' ? Number(val) : val;
    });

    const metodo = id ? 'PUT' : 'POST';
    const url = id ? `${API_URL}/${entidadeAtual}/${id}` : `${API_URL}/${entidadeAtual}`;

    try {
        const res = await fetch(url, {
            method: metodo,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });
        const data = await res.json();

        if (!res.ok) {
            mostrarFeedback(data.erro || 'Erro de validação.', 'erro');
            return;
        }

        mostrarFeedback(id ? 'Registro atualizado!' : 'Registro cadastrado!', 'sucesso');
        resetarFormulario();
        carregarDados();
    } catch {
        mostrarFeedback('Falha de conexão com o servidor.', 'erro');
    }
});

btnCancelar.addEventListener('click', resetarFormulario);

document.querySelectorAll('#menu-entidades button').forEach(btn => {
    btn.addEventListener('click', () => {
        document.querySelector('#menu-entidades button.active').classList.remove('active');
        btn.classList.add('active');
        entidadeAtual = btn.dataset.entity;
        renderizarFormulario();
        carregarDados();
    });
});

renderizarFormulario();
carregarDados();