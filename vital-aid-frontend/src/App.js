import './style/App.css';
import Home from './components/Home';
import Appoinment from './components/Appoinment';
import Auth from './components/Auth';
import Navbar from './components/Navbar';
import About from './components/About';
import Store from './components/Store';
import Profile from './components/Profile';
import Ambulance from './components/Ambulance';
import Doctors from './components/Doctors';
import DoctorDetails from './components/DoctorDetails';
import Hospitals from './components/Hospitals';
import HospitalDetails from './components/HospitalDetails';
import ForgotPass from './components/ForgotPass';
import ScrollToTop from './components/ScrollToTop';
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from 'react-router-dom';

function App() {
  return (
    <>
      <Router>

        <ScrollToTop />
        <Navbar />

        <Routes>
          <Route path='/' element={<Home />}></Route>
          <Route path='/appoinment' element={<Appoinment />}></Route>
          <Route path='/doctors' element={<Doctors />}></Route>
          <Route path='/doctor_details' element={<DoctorDetails />}></Route>
          <Route path='/hospitals' element={<Hospitals />}></Route>
          <Route path='/hospital_details' element={<HospitalDetails />}></Route>
          <Route path='/ambulance' element={<Ambulance />}></Route>
          <Route path='/about' element={<About />}></Route>
          <Route path='/store' element={<Store />}></Route>
          <Route path='/profile' element={<Profile />}></Route>
          <Route path='/login' element={<Auth />}></Route>
          <Route path='/forgotpassword' element={<ForgotPass />}></Route>
        </Routes>

      </Router>

    </>
  );
}

export default App;
