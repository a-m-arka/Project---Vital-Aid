import './App.scss';
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from 'react-router-dom';
import Sidebar from './components/sidebar/Sidebar';
import Dashboard from './pages/dashboard/Dashboard';
import Appointments from './pages/appointments/Appointments';
import AppointmentDetails from './pages/appointments/AppointmentDetails';
import Ratings from './pages/ratings/Ratings';
import ScrollToTop from './components/scrollToTop/ScrollToTop';
import Profile from './pages/profile/Profile';
import UpdateProfile from './pages/profile/UpdateProfile';
import ChangePassword from './pages/profile/ChangePassword';

function App() {
  return (
    <div className="App">

      <Router>
        <Sidebar />
        <div className="main">
          <ScrollToTop />
          <Routes>
            <Route path='/dashboard' element={<Dashboard />} />
            <Route path='/appointments' element={<Appointments />} />
            <Route path='/appointment-details' element={<AppointmentDetails />} />
            <Route path='/ratings' element={<Ratings />} />
            <Route path='/profile' element={<Profile />} />
            <Route path='/update-profile' element={<UpdateProfile />} />
            <Route path='/change-password' element={<ChangePassword />} />
          </Routes>
        </div>
      </Router>

    </div>
  );
}

export default App;
