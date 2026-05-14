import { useState } from 'react'
import { useEffect} from 'react'
import { useCallback} from "react";
import React from 'react'
import Dropzone, {useDropzone} from 'react-dropzone'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import axios from 'axios'
import './App.css'

const UserProfiles = () => {
    const [userProfiles, setUserProfiles] = useState([]);

    const fetchUserProfiles = () => {
        axios.get("http://34.238.139.203:8080/api/v1/user-profile").then((res) => {
            console.log(res);        // ← asta afisa obiectul intreg
            console.log(res.data);
            setUserProfiles(res.data);
        });
    }

    useEffect(() => {
        fetchUserProfiles();
    }, []);

    return userProfiles.map((userProfile, index) => {
        return (
            <div key={index}>
                {userProfile.userProfileId ? (
                    <img src={`http://34.238.139.203:8080/api/v1/user-profile/${userProfile.userProfileId}/image/download`} />
                ) : null}
                <br/>
                <h1>{userProfile.username}</h1>
                <p>{userProfile.userProfileId}</p>
                {/* ← trimiti fetchUserProfiles ca prop */}
                <MyDropzone {...userProfile} fetchUserProfiles={fetchUserProfiles}/>
                <br/>
            </div>
        )
    });
};

// Primesti fetchUserProfiles ca prop
function MyDropzone({ userProfileId, fetchUserProfiles }) {
    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];
        const formData = new FormData();
        formData.append("file", file);

        axios.post(
            `http://34.238.139.203:8080/api/v1/user-profile/${userProfileId}/image/upload`,
            formData,
            { headers: { 'Content-Type': 'multipart/form-data' } }
        ).then(() => {
            console.log("File successfully uploaded");
            fetchUserProfiles(); // ← reîncarcă userii după upload
        }).catch(err => {
            console.log(err)
        });

    }, [userProfileId]);

    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {isDragActive ?
                <p>Drop the image here ...</p> :
                <p>Drag 'n' drop profile image, or click to select profile image</p>
            }
        </div>
    )
}

function App() {
  return (
      <div className="App">
            <UserProfiles />
      </div>
      );
}

export default App;
