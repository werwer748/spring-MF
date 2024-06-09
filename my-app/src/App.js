import './App.css';
import { Container } from 'react-bootstrap';
import { Route, Routes } from 'react-router-dom';
import Header from './components/Header';
import Home from './pages/product/Home';
import SaveForm from './pages/product/SaveForm';
import Detail from './pages/product/Detail';
import JoinForm from './pages/user/JoinForm';
import UpdateForm from './pages/product/UpdateForm';
import LoginForm from './pages/user/LoginForm';

function App() {
  return (
    <div>
      <Header />
      <Container>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/saveForm" element={<SaveForm />} />
          <Route path="/product/:id" element={<Detail />} />
          <Route path="/joinForm" element={<JoinForm />} />
          <Route path="/loginForm" element={<LoginForm />} />
          <Route path="/updateForm/:id" element={<UpdateForm />} />
        </Routes>
      </Container>
    </div>
  );
}

export default App;
