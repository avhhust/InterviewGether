import React, { useState } from "react";
import photo from '../../assets/photo.jpeg'

export const ProfileIcon = (props) => {
  const [src, setSrc] = useState("");
  const [alt, setAlt] = useState("Photo");

  const { size } = props;

  const styles = {
    container: {
      display: 'flex',
      width: size || '50px',
      aspectRatio: '1/1'
    },
    img: {
      borderRadius: '50%',
      objectFit: 'cover'
    }
  }

  return (
      <div style={styles.container}>
        <img style={styles.img} src={photo} alt={alt} />
      </div>
  );
}
