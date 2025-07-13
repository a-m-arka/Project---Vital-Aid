import React from 'react'
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from 'recharts'

const CustomTooltip = ({ active, payload, label }) => {
  if (active && payload && payload.length) {
    return (
      <div style={{
        backgroundColor: 'var(--secondary-color)',
        border: '1px solid var(--text-color2)',
        padding: '10px',
        borderRadius: '5px',
        color: 'var(--text-color2)',
      }}>
        <p style={{ margin: 0 }}>{label}</p>
        <p style={{ margin: 0 }}>
          <strong>{payload[0].name || payload[0].dataKey}:</strong> {payload[0].value}
        </p>
      </div>
    )
  }

  return null
}

const Graph = ({ data, xDataKey, yDataKey }) => {
  return (
    <div style={{ width: '100%', height: '100%' }}>
      <ResponsiveContainer width="100%" height="100%">
        <LineChart data={data}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey={xDataKey} />
          <YAxis allowDecimals={false} />
          <Tooltip content={<CustomTooltip />} />
          <Legend />
          <Line type="linear" dataKey={yDataKey} stroke="var(--primary-color)" strokeWidth={3} />
        </LineChart>
      </ResponsiveContainer>
    </div>
  )
}

export default Graph
