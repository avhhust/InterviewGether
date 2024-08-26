import React from 'react';

const Button = (props) => {
    const {id, label, type, onClick, url, className} = props;

    const styles = {
        display: 'block',
        width: 'fit-content',
        padding: '5px 10px',
        borderRadius: '50px',
        border: '1px solid',
        cursor: 'pointer',
        background: 'none',
    }

    const button = (
        <button id={id} style={styles} onClick={onClick} className={className}>
            {label}
        </button>
    );

    const link = (
        <a id={id} style={styles} onClick={onClick} href={url || ''} className={className}>
            {label}
        </a>
    )

    return (
        type === 'link' ? link : button
    )
}

export default Button;