import React from 'react'

export const SpinnerLoading = () => {
  return (
    <div className='container m-5 d-flex justify-content-center'
        style={{height: 550}}>
            <div className='spinner-border txt-primary' role='status'>
                <span className='visually-hidden'>
                    Teka lang loading pa...
                </span>
            </div>
    </div>
  )
}
