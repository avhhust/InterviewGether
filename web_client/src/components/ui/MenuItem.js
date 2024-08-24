import React from 'react'
import { Link } from 'react-router-dom';

export const MenuItem = (props) => {
  const {icon, url, name} = props;

  const styles = {
    container: {
      height: '42px',
      paddingLeft: '12px',
      display: 'flex',
      alignItems: 'center',
      borderRadius: '8px',
      backgroundColor: 'rgba(242, 242, 242, 0.50)',
      fontSize: '0.8em',
      fontWeight: 'bold'
    },
    icon: {
      transform: 'scale(1.3)',
      marginRight: '7px'
    }
  }

  return (
    <div style={styles.container}>
      <div style={styles.icon}>{icon}</div>
      <Link to={url} style={{padding: '1em', margin: '-1em'}}>{name}</Link>
    </div>
  )
}
