import './style/App.css';
import Home from './components/Home';
import Appoinment from './components/Appoinment';
import Auth from './components/Auth';
import Navbar from './components/Navbar';
import About from './components/About';
import Store from './components/Store';
import Ambulance from './components/Ambulance';
import AmbulanceForm from './components/AmbulanceForm';
import Doctors from './components/Doctors';
import DoctorDetails from './components/DoctorDetails';
import Hospitals from './components/Hospitals';
import HospitalDetails from './components/HospitalDetails';
import ForgotPass from './components/ForgotPass';
import ScrollToTop from './components/ScrollToTop';
import ViewProfile from './components/UserProfile/View';
import PrivateRoute from './components/PrivateRoute';
import OrderList from './components/OrderList';
import AppoinmentList from './components/AppoinmentList';
import AppoinmentDetails from './components/AppoinmentDetails';
import HospitalDoctors from './components/HospitalDoctors';

// import { useGlobalContext } from './context/GlobalContext';
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from 'react-router-dom';


function App() {

  // localStorage.removeItem('token');
  // const token = localStorage.getItem('token');
  // console.log('Token:', token);

  return (
    <>
      <Router>

        <ScrollToTop />
        <Navbar />

        <Routes>
          <Route path='/' element={<Home />}></Route>
          <Route element={<PrivateRoute />}>
            <Route path='/appointment' element={<Appoinment />}></Route>
          </Route>
          <Route element={<PrivateRoute />}>
            <Route path='/appointmentlist' element={<AppoinmentList />} />
          </Route>
          <Route element={<PrivateRoute />}>
            <Route path='/appointmentdetails' element={<AppoinmentDetails />} />
          </Route>
          <Route path='/doctors' element={<Doctors />}></Route>
          <Route path='/doctor_details' element={<DoctorDetails />}></Route>
          <Route path='/hospitals' element={<Hospitals />}></Route>
          <Route path='/hospital_details' element={<HospitalDetails />}></Route>
          <Route path='/hospital_doctors' element={<HospitalDoctors />} />
          <Route path='/ambulance' element={<Ambulance />}></Route>
          <Route element={<PrivateRoute />}>
            <Route path='/ambulanceform' element={<AmbulanceForm />}></Route>
          </Route>
          <Route path='/about' element={<About />}></Route>
          <Route path='/store' element={<Store />}></Route>
          <Route element={<PrivateRoute />}>
            <Route path='/orders' element={<OrderList />} />
          </Route>
          <Route element={<PrivateRoute />}>
            <Route path='/profile' element={<ViewProfile />} />
          </Route>
          <Route path='/login' element={<Auth />}></Route>
          <Route path='/forgotpassword' element={<ForgotPass />}></Route>
        </Routes>

      </Router>

    </>
  );
}

export default App;
