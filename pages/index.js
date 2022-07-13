import Head from 'next/head'
import Image from 'next/image'
import styles from '../styles/Home.module.css'

import Header from './component/page_component/header/Header';
import NavigationPanel from './component/page_component/nav_panel/NavigationPanel';
import Content from './component/page_component/content/Content';

export default function Home() {
  return (
    <div className={styles.container}>

      <Header/>
      <NavigationPanel/>
      <Content/>


     
    </div>
  )
}
