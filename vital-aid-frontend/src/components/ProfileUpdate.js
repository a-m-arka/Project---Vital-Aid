import React from 'react'

export default function ProfileUpdate({ onCancel }) {
    return (
        <div className="update-section pop-out">
            <form>
                <div className="form-Row">
                    <div className="update-section-input-Data">
                        <input type="text" required />
                        <label>Name</label>
                    </div>
                    <div className="update-section-input-Data">
                        <input type="number" required />
                        <label>Phone Number</label>
                    </div>
                </div>
                <div className="form-Row">
                    <div className="update-section-input-Data">
                        <input type="text" required />
                        <label>Gender</label>
                    </div>
                    <div className="update-section-input-Data" id='date-of-birth'>
                        <input
                            type="text"
                            onFocus={(e) => (e.target.type = 'date')}
                            onBlur={(e) => !e.target.value && (e.target.type = 'text')}
                            placeholder=""
                            required
                        />
                        <label>Date of Birth</label>
                    </div>
                </div>
                <div className="form-Row">
                    <div className="update-section-input-Data">
                        <input type="email" required />
                        <label>Email</label>
                    </div>
                    <div className="update-section-input-Data">
                        <input type="text" required />
                        <label>Blood Group</label>
                    </div>
                </div>
                <div className="form-Row adressText">
                    <div className="update-section-input-Data">
                        <input type="text" required />
                        <label>Address</label>
                    </div>
                </div>
                <div className="form-Row Update-cancel-btn">
                    <div className="update-section-input-Data">
                        <input type="submit" value="Update" />
                    </div>
                    <div className="update-section-input-Data">
                        <input type="submit" value="Cancel" onClick={onCancel} />
                    </div>
                </div>
            </form>
        </div>
    )
}
