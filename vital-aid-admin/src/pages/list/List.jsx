import React from 'react'
import './List.scss'
import Sidebar from '../../components/sidebar/Sidebar'
import Datatable from '../../components/datatable/Datatable'
import { useNavigate } from 'react-router-dom'

const List = ({ type, rows, columns, adminControl }) => {

  const navigate = useNavigate();
  const handleAddClick = (row) => {
    navigate(`/${type}/new`, { state: { row } });
  };

  return (
    <div className='list'>

      <Sidebar />

      <div className="listContainer">
        <div className="top">
          <h1>{String(type).toUpperCase()} LIST</h1>
          {adminControl && (
            <div className="addButton" onClick={() => handleAddClick(rows[0])}>
              Add New
            </div>
          )}
        </div>
        <Datatable type={type} rows={adminControl ? rows.slice(1) : rows} columns={columns} adminControl={adminControl} />
      </div>

    </div>
  )
}

export default List